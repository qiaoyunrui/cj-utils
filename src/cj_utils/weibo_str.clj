(ns cj-utils.weibo-str)

(require '[cj-utils.utils.matrix :as matrix])

(def max-length 5)                                          ;横向最大长度
(def separator "|")                                         ;分割符
(def padding " ")                                           ;填充物
(def ascii-count 127)                                       ;ASCII 码个数

(defn ascii? [index]
  "是否为 ascii 表中的字符"
  (and (< index ascii-count) (>= index 0)))

(defn fill-char [c]
  "对字符进行填充"
  (str c padding))

(defn convert [c]
  "统一转换"
  (let [index (int c)]
    (if (ascii? index)
      (fill-char c)
      c)))

(defn get-line-count [content]
  "获取行数"
  (int (Math/ceil (/ (count content) max-length))))


;对字符串进行重排序
(defn re-arrange [content]
  "对字符串进行重排序"
  (let [line-count (get-line-count content)]))



(defn convert-2D-array [content]
  "转换为二维数组"
  (first (reduce (fn [[array index] item]
                   (let [new-item (convert item)
                         current-line (int (/ index max-length))
                         current-row (mod index max-length)]
                     [(matrix/update-value array current-line current-row new-item) (inc index)]))
                 [(matrix/build-2d-array (get-line-count content) max-length) 0]
                 (seq content))))

(defn weibo-str [content]
  (clojure.string/join "\n"
                       (map #(clojure.string/join separator %)
                            (matrix/cols (convert-2D-array content)))))

