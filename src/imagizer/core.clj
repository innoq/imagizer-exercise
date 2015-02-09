(ns imagizer.core
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [imagizer.images.retrieval :as ir]
            [imagizer.view :as v]))

(defn images-page [url]
  (let [images (-> url ir/load-html ir/parse-to-hiccup ir/imgs)
        sources-and-alts (->> images
                              (filter #(.startsWith (ir/src %) "http"))
                              (remove #(.startsWith (ir/src %) "https"))
                              (map (juxt ir/src ir/alt)))]
    (v/images-page url sources-and-alts)))

(defroutes app-routes
  (GET "/" [] v/homepage)
  (GET "/images" [url] (images-page url))
  (route/resources "/")
  (route/not-found "oops - not found"))

(defn wrap-default-content-type [handler content-type]
  (fn [req]
    (let [resp (handler req)]
      (if (contains? (:headers resp) "Content-Type")
        resp
        (assoc-in resp [:headers "Content-Type"] content-type)))))

(def webapp (-> app-routes
                wrap-json-response
                (wrap-defaults (assoc-in api-defaults [:responses :content-types] false))
                wrap-file-info
                (wrap-default-content-type "text/html")))
