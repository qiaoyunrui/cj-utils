(ns cj-utils.core
  (:gen-class))

(require '[cj-utils.weibo-str :as weibo])

(def content "大家好，我是鹿晗，这是我的女朋友关晓彤。")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (weibo/weibo-str content)))
