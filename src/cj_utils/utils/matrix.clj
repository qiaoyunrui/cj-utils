(ns cj-utils.utils.matrix)

(defprotocol Matrix
  "矩阵协议"
  (get-value [matrix i j])
  (update-value [matrix i j value])
  (rows [matrix])
  (cols [matrix])
  (dims [matrix]))

;扩展协议
(extend-protocol Matrix
  clojure.lang.IPersistentVector
  (get-value [vov i j]
    (get-in vov [i j]))
  (update-value [vov i j value]
    (assoc-in vov [i j] value))
  (rows [vov]
    (seq vov))
  (cols [vov]
    (apply map vector vov))
  (dims [vov]
    [(count vov) (count (first vov))]))

;默认实现
(extend-protocol Matrix
  nil
  (get-value [x i j])
  (update-value [x i j value])
  (rows [x] [])
  (cols [x] [])
  (dims [x] [0 0]))

(defn build-2d-array
  "生成一个二维容器 h - 行 w - 列"
  ([h w]
   (build-2d-array h w nil))
  ([h w init-value]
   (vec (repeat h (vec (repeat w init-value))))))