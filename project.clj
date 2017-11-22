(defproject work "0.1.0-SNAPSHOT"
            :dependencies [[org.clojure/clojure "1.9.0-RC1"]
                           [org.clojure/clojurescript "1.9.946"]
                           [reagent "0.7.0"]
                           [re-frame "0.10.2"]]

            :plugins [[lein-cljsbuild "1.1.7"]]

            :min-lein-version "2.5.3"

            :source-paths ["src/clj"]

            :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                              "test/js"]

            :figwheel {:css-dirs ["resources/public/css"]}

            :profiles
            {:dev
             {:dependencies [[binaryage/devtools "0.9.4"]]

              :plugins [[lein-figwheel "0.5.14"]
                        [lein-doo "0.1.8"]]}}

            :cljsbuild
            {:builds
             [{:id "dev"
               :source-paths ["src/cljs"]
               :figwheel {:on-jsload "work.core/mount-root"}
               :compiler {:main work.core
                          :output-to "resources/public/js/compiled/app.js"
                          :output-dir "resources/public/js/compiled/out"
                          :asset-path "js/compiled/out"
                          :source-map-timestamp true
                          :preloads [devtools.preload]
                          :external-config {:devtools/config {:features-to-install :all}}
                          }}

              {:id "min"
               :source-paths ["src/cljs"]
               :compiler {:main work.core
                          :output-dir "resources/public/js/compiled"
                          :output-to "resources/public/js/compiled/app.js"
                          :optimizations :advanced
                          :modules {
                                    ; From https://clojurescript.org/reference/compiler-options#modules
                                    ; The :cljs-base module defaults to being written out to :output-dir
                                    ; with the name "cljs_base.js". This may be overridden by specifying a
                                    ; :cljs-base module describing only :output-to.


                                    ; ^^^^^^^^^^^^^^^^^^^^
                                    ; But throws a compile error:
                                    ; Missing required key :entries at path (:cljsbuild :builds 1 :compiler :modules :cljs-base)
                                    ;{:cljsbuild
                                    ; {:builds
                                    ;  [{:compiler
                                    ;    {:modules
                                    ;     {:cljs-base
                                    ;      {:output-to "resources/public/js/compiled/app.js"
                                    ;       :entries ...
                                    ;       ^---- The required key :entries is missing
                                    ;       }}}}]}}

                                    :outside {:entries #{outside.core}
                                              :output-to "resources/public/js/compiled/outside.js"}}

                          :closure-defines {goog.DEBUG false}
                          :pretty-print false}}

              {:id "test"
               :source-paths ["src/cljs" "test/cljs"]
               :compiler {:main work.runner
                          :output-to "resources/public/js/compiled/test.js"
                          :output-dir "resources/public/js/compiled/test/out"
                          :optimizations :none}}
              ]}

            )
