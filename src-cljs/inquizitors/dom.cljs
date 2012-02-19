(ns inquizitors.dom
  (:use [jayq.core :only [$]]))

(defn on-ready [f]
  (.ready ($ js/document) f))

(defn on-user-input [f]
  (-> ($ :#chatbox)
    (.submit
      (fn [e] (.preventDefault e)
              (f (user-input))))))

(defn clear-user-input [f]
  (-> ($ :#message)
    (.val "")))

(defn user-input []
  (.val ($ :#message)))

(defn append-to-log [msg]
  (-> ($ :#log)
    (.append (str msg "<br>"))))