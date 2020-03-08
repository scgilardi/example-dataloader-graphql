(ns graphql-starwars.components.main
  (:require [clojure.string :as str]
            [graphql-starwars.route :as route]
            [sazhet.ui :refer [<comp map> sub>]
             :refer-macros [evt>]]))

(def columns ^{:map>/key-fn identity}
  [:films
   :species
   :starships
   :people
   :planets
   :vehicles])

(defn title
  [column]
  (str/capitalize (name column)))

(defn toggle-column [ctx selected? column]
  (route/set-selected ctx (if (selected? column)
                            (disj selected? column)
                            (conj selected? column))))

(defn render-navbar [ctx selected?]
  [:nav.border.navbar.navbar-light.bg-faded>div
   (map> (fn [column]
           [:label.mr-4
            [:input
             {:type :checkbox
              :checked (boolean (selected? column))
              :on-change (evt> (toggle-column ctx selected? column))}]
            [:span.ml-1.d-inline-block (title column)]])
         columns)])

(defn render-column [ctx column]
  [:div.col>div.card
   [:div.card-block>h4.card-title (title column)]
   [:ul.list-group.list-group-flush
    (map> (fn [item]
            [:li.list-group-item (or (:name item) (:title item))])
          (sub> ctx column))]])

(defn render-columns [ctx selected?]
  [:div.container-fluid.mt-4>div.row
   (map> (partial render-column ctx)
         (with-meta (filter selected? columns) (meta columns)))])

(defn render [ctx]
  (let [selected? (route/get-selected ctx)]
    [:<>
     [render-navbar ctx selected?]
     [render-columns ctx selected?]]))

(def component
  (<comp :renderer render
         :subscription-deps columns))
