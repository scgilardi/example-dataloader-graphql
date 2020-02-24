(defproject graphql-starwars "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [reagent "0.9.1"]
                 [keechma "0.3.14"
                  :exclusions [cljsjs/react-with-addons
                               cljsjs/react-dom
                               cljsjs/react-dom-server]]
                 [keechma/toolbox "0.1.24-sq1-SNAPSHOT"]
                 [binaryage/devtools "1.0.0"]
                 [funcool/promesa "5.1.0"]
                 [http-kit "2.3.0"]
                 [cljs-ajax "0.8.0"]
                 [floatingpointio/graphql-builder "0.1.10"]
                 [medley "1.2.0"]]

  :min-lein-version "2.5.3"

  :source-paths ["src"]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies [[figwheel-sidecar "0.5.19"]]
    :plugins      [[lein-figwheel "0.5.19"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src"]
     :figwheel     {:on-jsload "graphql-starwars.core/reload"}
     :compiler     {:main                 graphql-starwars.core
                    :optimizations        :none
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/dev"
                    :asset-path           "js/compiled/dev"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src"]
     :compiler     {:main            graphql-starwars.core
                    :optimizations   :advanced
                    :output-to       "resources/public/js/compiled/app.js"
                    :output-dir      "resources/public/js/compiled/min"
                    :elide-asserts   true
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    ]})
