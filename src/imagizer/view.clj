(ns imagizer.view
  (:require [hiccup.page :as hiccup]
            [hiccup.form :as form]
            [ring.util.response :as response]))

(def baseurl "http://localhost:3000")

(defn layout [& content]
  (response/response 
   (hiccup/html5
    [:head
     (hiccup/include-css 
      "http://fonts.googleapis.com/css?family=Montserrat:700,400" 
      "/stylesheets/imagizer.css")
     [:base {:href baseurl}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:meta {:charset "utf-8"}]
     [:title "imagizer"]]
    [:body 
     [:div.nav 
      [:div.nav-content
       [:a {:href "/"}
        [:img {:src "/img/icon.svg"}]
        [:span "Imagizer"]]]
      ]
     [:div.content 
      content]
     (hiccup/include-js "/js/imagizer.js")])))

(defn search-form [url]
  (form/form-to [:get "/images"]
                [:p (form/text-field {:size 50} "url" url)]
                [:p (form/submit-button "search")]))

(def homepage
  (layout
   [:p "Search for images"]
   (search-form "http://picjumbo.com/category/animals/")))

(defn images-page [url sources-and-alts]
  (layout [:h1 "search result"]
          (search-form url)
          [:ul.result-list
           (map (fn [[src alt]]
                  [:li.result
                   [:a {:href (str "/image?src=" src)}
                    [:img {:src src :alt alt}]]])
                sources-and-alts)]))
