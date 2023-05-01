(ns datahike-cassandra.core-test
 (:require [clojure.test :as t :refer [deftest use-fixtures]]
           [datahike.integration-test :refer [integration-test integration-test-fixture]]
           [datahike-cassandra.core]))

(def config {:store {:backend :cassandra
                     :session-keyspace "datahike_cassandra_integration_test"}
             :schema-flexibility :write
             :keep-history? true})

(defn fixture [f]
  (integration-test-fixture config)
  (f))

(use-fixtures :once fixture)

(deftest  datahike-integration-test
  (integration-test config))
