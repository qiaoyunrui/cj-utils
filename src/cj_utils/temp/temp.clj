(ns cj-utils.temp.temp)

(defmulti fill
          "Fill a xml/html node (as per clojure.xml) with the provided value"
          (fn [node value] (:tag node)))                    ;这是转发函数。传递给多重方法的参数被传递给这个函数，产生一个转发值，这个值用于为这些参数选择调用哪种方法

(defmethod fill :div
  [node value]
  (assoc node :content [(str value)]))                      ;这里的 :div 是一个转发值，

(defmethod fill :input
  [node value]
  (assoc-in node [:attrs :value] (str value)))