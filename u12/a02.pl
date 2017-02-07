%Benoetigt a10 aus letzter Abgabe

%a)
gleich(X,X). %Gleichheit
isFirst(cons(Piste,Rest),F) :- gleich(Piste,F). %zur pruefung, ob Folgeelemnte auch Folgepiste ist

pathOfLength(nil, 0).
pathOfLength(cons(tal,nil),0).
pathOfLength(cons(Piste, Rest), s(N)) :- endetIn(Piste, F), isFirst(Rest, F), pathOfLength(Rest, N).
