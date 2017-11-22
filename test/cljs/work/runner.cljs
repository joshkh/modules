(ns work.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [work.core-test]))

(doo-tests 'work.core-test)
