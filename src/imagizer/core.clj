(ns imagizer.core
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.page :as hiccup]
            [imagizer.view :as v]))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "oops - not found"))

(defn wrap-default-content-type [handler content-type]
  (fn [req]
    (let [resp (handler req)]
      (if (contains? (:headers resp) "Content-Type")
        resp
        (assoc-in resp [:headers "Content-Type"] content-type)))))

(def webapp (-> app-routes
                (wrap-default-content-type "text/html")))
