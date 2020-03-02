(ns graphql-starwars.route
  (:require [graphql-starwars.ui :refer [<route route>]]))

(defn get-selected
  "Returns the set of `selected` columns specified by the current route."
  [ctx]
  (into #{} (map keyword) (route> ctx :columns)))

(defn set-selected
  "Redirects to a route that specfies the set of `selected` columns."
  [ctx selected]
  (<route ctx :columns (into [] (map name) selected)))
