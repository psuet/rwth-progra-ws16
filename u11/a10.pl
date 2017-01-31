%a
blau(sonnalm).
blau(vorkogel).
blau(arbiskogel).
blau(plattenalm).
blau(wiesenalm).
schwarz(teufeltal).
rot(isskogel).

start(sonnalm).
start(teufeltal).

endetIn(sonnalm,vorkogel).
endetIn(sonnalm,arbiskogel).

endetIn(vorkogel, isskogel).

endetIn(arbiskogel, plattenalm).

endetIn(plattenalm, wiesenalm).

endetIn(teufeltal,wiesenalm).

endetIn(wiesenalm,tal).

endetIn(isskogel,tal).

%b
%endetIn(X,wiesenalm).
%X = plattenalm ;
%X = teufeltal.

%c
gleicherstartpunkt(X,Y) :- endetIn(Z,X), endetIn(Z,Y).
gleicherstartpunkt(X,Y) :- start(X), start(Y).