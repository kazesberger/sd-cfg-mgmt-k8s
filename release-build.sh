#!/usr/bin/env bash

./install-reveal.js.sh

rm -rf target

clojure -m figwheel.main -O simple -bo dev

cp -r resources/public/* target/public/
