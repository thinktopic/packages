(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.7.2" :scope "test"]
                  [cljsjs/react "15.6.1-1"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "15.5.10")
(def +version+ (str +lib-version+ "-1"))

(task-options!
 pom  {:project     'cljsjs/prop-types
       :version     +version+
       :description "Runtime type checking for React props and similar objects"
       :url         "https://github.com/facebook/prop-types"
       :scm         {:url "https://github.com/cljsjs/packages"}
       :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(deftask package []
  (comp
    (download :url (format "https://unpkg.com/prop-types@%s/prop-types.js" +lib-version+)
              :target "cljsjs/prop-types/development/prop-types.inc.js")
    (download :url (format "https://unpkg.com/prop-types@%s/prop-types.min.js" +lib-version+)
              :target "cljsjs/prop-types/production/prop-types.min.inc.js")
    (deps-cljs :provides ["prop-types" "cljsjs.prop-types"]
               :global-exports '{prop-types PropTypes})
    (pom)
    (jar)))
