(ns work.views
  (:require [re-frame.core :as re-frame]
            [work.subs :as subs]
            [cljs.loader :as loader]
            [goog.dom :as gdom]
            [goog.events :as events])
  (:import [goog.events EventType]))



(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:button
     {:on-click (fn [e] (loader/load :outside
                               (fn [e]
                                 ((resolve 'work.admin/init!)))))}
      "Load Outside"]))
