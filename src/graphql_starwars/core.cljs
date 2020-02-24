(ns graphql-starwars.core
  (:require
   [keechma.app-state :as app-state]
   [graphql-starwars.components :as components]
   [graphql-starwars.controllers :as controllers]
   [graphql-starwars.subscriptions :as subscriptions]))

(def app-definition
  {:components    components/system
   :controllers   controllers/controllers
   :subscriptions subscriptions/subscriptions
   :html-element  (.getElementById js/document "app")})

(defonce running-app (clojure.core/atom nil))

(defn start-app! []
  (reset! running-app (app-state/start! app-definition)))

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn reload []
  (let [current @running-app]
    (if current
      (app-state/stop! current start-app!)
      (start-app!))))

(defn ^:export main []
  (dev-setup)
  (start-app!))
