#!/usr/bin/env zsh

./install-reveal.js.sh

rm -rf target

clojure -m figwheel.main -O simple -bo dev

cp -r resources/public/* target/public/

pushd target/public

git init
git add .
git commit -m "update gh-pages"
git push -f https://github.com/kazesberger/sd-cfg-mgmt-k8s.git master:gh-pages

rm -rf .git

popd
