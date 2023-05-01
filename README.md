# Cassandra Backend for Datahike

This is a backend for Datahike that enables you to utilize an [Apache Cassandra](https://cassandra.apache.org/) as a durable store.

## Status
<p align="center">
<a href="https://clojurians.slack.com/archives/CB7GJAN0L"><img src="https://badgen.net/badge/-/slack?icon=slack&label"/></a>
<a href="https://clojars.org/org.clojars.timokramer/datahike-cassandra"> <img src="https://img.shields.io/clojars/v/org.clojars.timokramer/datahike-cassandra.svg" /></a>
<a href="https://circleci.com/gh/org.clojars.timokramer/datahike-cassandra"><img src="https://circleci.com/gh/org.clojars.timokramer/datahike-cassandra.svg?style=shield"/></a>
<a href="https://github.com/org.clojars.timokramer/datahike-cassandra/tree/development"><img src="https://img.shields.io/github/last-commit/org.clojars.timokramer/datahike-cassandra/development"/></a>
</p>

## Configuration
Provide a configuration like this:
```
(def config {:store {:backend :cassandra
                     :session-keyspace "datahike_cassandra_integration_test"}
             :schema-flexibility :write
             :keep-history? true})
```
You can find information on possible configuration for your [Cassandra on the DataStax website](https://docs.datastax.com/en/developer/java-driver/4.6/manual/core/configuration/).
And you can find information for [configuring Datahike on cljdoc](https://cljdoc.org/d/io.replikativ/datahike/0.6.1540/doc/datahike-database-configuration).

## Development and Testing
Run a cassandra container with your favorite container runtime e.g. [Podman](https://podman.io/).
```bash
podman run --detach --publish 9042:9042 --name cassandra docker.io/cassandra:4.1
```

Run the tests with `./bin/kaocha`.

## Licensing
datahike-cassandra is licensed under [Eclipse Public License - v 1.0](https://github.com/replikativ/datahike/blob/master/LICENSE).
