%Benoetigt a10 aus letzter Abgabe
blau(vorkogel).
blau(sonnalm).
blau(arbiskogel).
blau(plattenalm).
blau(wiesenalm).
rot(isskogel).
schwarz(teufeltal).

start(sonnalm).
start(teufeltal).

endetIn(sonnalm, arbiskogel).
endetIn(sonnalm, vorkogel).
endetIn(vorkogel, isskogel).
endetIn(isskogel, tal).
endetIn(arbiskogel, plattenalm).
endetIn(plattenalm, wiesenalm).
endetIn(teufeltal, wiesenalm).
endetIn(wiesenalm, tal).

%a)
gleich(X,X). %Gleichheit
isFirst(cons(Piste,Rest),F) :- gleich(Piste,F). %zur pruefung, ob Folgeelemnte auch Folgepiste ist

pathOfLength(nil, 0).
pathOfLength(cons(tal,nil),0).
pathOfLength(cons(Piste, Rest), s(N)) :- endetIn(Piste, F), isFirst(Rest, F), pathOfLength(Rest, N).

%b
tourOfLength(cons(tal, nil), 0).
tourOfLength(cons(tal, cons(Punkt, Rest)), L) :- start(Punkt), tourOfLength2(cons(Punkt, Rest), L).
tourOfLength2(cons(tal, nil), 0).
tourOfLength2(cons(tal, cons(Punkt, Rest)), L) :- start(Punkt), tourOfLength2(cons(Punkt, Rest), L).
tourOfLength2(cons(Punkt, cons(Punkt2, Rest)), s(L)) :- endetIn(Punkt, Punkt2), tourOfLength2(cons(Punkt2, Rest), L).

%c
convert(nil, []).
convert(cons(Datum, Rest), [Datum|Rest2]) :- convert(Rest, Rest2).

%d
enumerateTours(T) :- enumerateTours(T, 0).
enumerateTours(T, L) :- tourOfLength(X, L), convert(X, T).
enumerateTours(T, L) :- enumerateTours(T, s(L)).

%e)
tourRotSchwarz([tal], 0, 0).
tourRotSchwarz([tal|Rest], Rot, Schwarz) :- enumerateTours([tal|Rest]), tourRotSchwarz2(Rest, Rot, Schwarz).
tourRotSchwarz2([tal], 0, 0).
tourRotSchwarz2([tal|Rest], Rot, Schwarz) :- tourRotSchwarz2(Rest, Rot, Schwarz).
tourRotSchwarz2([Punkt|Rest], Rot, s(Schwarz)) :- schwarz(Punkt), tourRotSchwarz2(Rest, Rot, Schwarz).
tourRotSchwarz2([Punkt|Rest], s(Rot), Schwarz) :- rot(Punkt), tourRotSchwarz2(Rest, Rot, Schwarz).
tourRotSchwarz2([Punkt|Rest], Rot, Schwarz) :- blau(Punkt), tourRotSchwarz2(Rest, Rot, Schwarz).

