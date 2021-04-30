(ns datahike-cassandra.core-test
 (:require
    [clojure.test :refer [is deftest use-fixtures]]
    [clojure.core.async :refer [<!!]]
    [konserve-cassandra.io :as io]
    [datahike.api :as d]
    [datahike.integration-test :as it]
    [datahike-cassandra.core]))

(def config {:store {:backend :cassandra
                     :session-keyspace "konserve"
                     :contact-points ["127.0.0.1"]
                     :keyspace {:replication {:class "SimpleStrategy" :replication_factor 1}}}
             :schema-flexibility :write
             :keep-history? true})

(defn config-record-test-fixture [f]
  (let [session (io/connect (io/cluster (:store config)))]
    (io/create-keyspace session config))
  (it/integration-test-fixture config)
  (f))

(use-fixtures :once config-record-test-fixture)

(deftest ^:integration config-record-test []
  (it/integration-test config))
