// Compiled by ClojureScript 1.10.516 {}
goog.provide('reveal.core');
goog.require('cljs.core');
goog.require('clojure.string');
goog.require('goog.dom');
goog.require('hiccups.runtime');
goog.require('reveal.slides');
reveal.core.options = cljs.core.clj__GT_js.call(null,new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"controls","controls",1340701452),true,new cljs.core.Keyword(null,"progress","progress",244323547),true,new cljs.core.Keyword(null,"transition","transition",765692007),"fade",new cljs.core.Keyword(null,"slideNumber","slideNumber",1553611975),false,new cljs.core.Keyword(null,"dependencies","dependencies",1108064605),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"src","src",-1651076051),"plugin/notes/notes.js",new cljs.core.Keyword(null,"async","async",1050769601),true], null)], null)], null));
/**
 * Get list of all slides and convert them to html strings.
 */
reveal.core.convert = (function reveal$core$convert(){
var slides = reveal.slides.all.call(null);
return clojure.string.join.call(null,cljs.core.map.call(null,((function (slides){
return (function (p1__12632_SHARP_){
return cljs.core.str.cljs$core$IFn$_invoke$arity$1(hiccups.runtime.render_html.call(null,p1__12632_SHARP_));
});})(slides))
,slides));
});
/**
 * Get all slides, set them as innerHTML and reinitialize Reveal.js
 */
reveal.core.main = (function reveal$core$main(){
goog.dom.getElement("slides").innerHTML = reveal.core.convert.call(null);

Reveal.initialize(reveal.core.options);

return Reveal.setState(Reveal.getState());
});
reveal.core.main.call(null);
