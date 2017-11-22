(ns outside.core
  (:require [reagent.core :as r]))

(defn main []
  (fn []
    [:h1 "Admin"]))

(defn init! []
  (println "ADMIN INITED"))
