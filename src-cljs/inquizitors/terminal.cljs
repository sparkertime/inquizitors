(ns inquizitors.terminal
  (:require [clojure.string :as string]))

(def canvas  (.getElementById js/document "terminal"))
(def context (.getContext canvas "2d"))
(def width (.-width canvas))
(def height (.-height canvas))
(def x-scale 10)
(def y-scale 18)

(defn set-fill [& rgba]
  (set! (.-fillStyle context) (str "rgb(" (string/join "," rgba) ")")))

(defn rect [x y width height]
  (.fillRect context x y width height))

(defn clear
  ([] (clear 0 0 width height))
  ([x y width height]
    (set-fill 0 0 0)
    (rect x y width height)))

(defn text [msg x y]
  (set! (.-font context) " 20px 'Anonymous Pro', 'Andale Mono', monospace")
  (set! (.-textBaseline context) "top")
  (set-fill 180 180 180)
  (.fillText context msg x y))

(defn draw-glyph [character x y]
  (let [x-actual (* x-scale x)
        y-actual (* y-scale y)]
    (text character x-actual y-actual)))

(defn draw-screen [all-characters x-boundary]
  (clear)
    (doseq [i (range 0 (count all-characters))]
      (draw-glyph (nth all-characters i) (mod i x-boundary) (.floor js/Math (/ i x-boundary)))))
