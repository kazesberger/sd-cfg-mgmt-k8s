#!/usr/bin/env bash

yarn install
cp -r resources/public/node_modules/reveal.js/plugin resources/public/
cp -r resources/public/node_modules/reveal.js/css resources/public/
cp -r resources/public/node_modules/reveal.js/js resources/public/
cp -r resources/public/node_modules/reveal.js/lib resources/public/

rm -rf resources/public/node_modules