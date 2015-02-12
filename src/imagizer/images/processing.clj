(ns imagizer.images.processing
  (:require [yesql.core :refer [defquery defqueries]])
  (:import [javax.imageio ImageIO]
           [java.awt.image ConvolveOp BufferedImage Kernel]
           [java.io File]))

(defquery get-image "db/get_image.sql")
(defquery get-images-by-tag "db/get_images_by_tag.sql")

(defqueries "db/add_image.sql")

(def edge-dect-filter
  (float-array [-1 -1 -1 
                -1 8 -1
                -1 -1 -1]))

(def emboss-filter
  (float-array [-2 -1 0 
                -1 1 1
                -0 1 2]))

(def blur-filter
  (float-array (map #(* 1/9 %) [1 1 1 
                                1 1 1
                                1 1 1])))

(def sharpen-filter
  (float-array [0 -1 0 
                -1 5 -1
                0 -1 0]))

(def other1-filter
  (float-array [-0.06461716 0.2576051 -0.3202408
                0.71336395 0.81451154 -0.12557723
                -0.9529113 0.17054553 0.9627515]))

(def other2-filter
  (float-array [-0.08744701 0.5747784 -0.3541638 
                0.5890945 0.21095711 -0.28101382 
                0.99041694 0.53377277 0.2925806]))

(def other3-filter
  (float-array [2 1 0
                0 0 0
                0 -2 -2]))

(def conversions (sorted-map
                  :blur blur-filter
                  :edge-detection edge-dect-filter
                  :sharpen sharpen-filter
                  :emboss emboss-filter
                  :other1 other1-filter
                  :other2 other2-filter
                  :other3 other3-filter))

(defn converter [filter]
  (fn [from to]
    (println "*** Apply filter *** " (java.util.Arrays/toString filter))
    (let [src (ImageIO/read (File. from))
          op (ConvolveOp. (Kernel. 3, 3, filter))
          dest (File. to)
          img (.filter op src nil)]
      (ImageIO/write img "jpg" dest))))

(defn conversion [op]
  ((keyword op) conversions))


