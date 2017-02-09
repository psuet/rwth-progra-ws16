% Aufgabe 8

powers(X, 1, [X]) :- X>0.
powers(X, N, [A|R]) :- power(X, N, A), N>0, X>0, NewN is N - 1, powers(X, NewN, R).

power(_, 0, 1).
power(Base, 1, Base).
power(Base, Exp, Res) :- Exp > 0, NewExp is Exp - 1, power(Base, NewExp, NewRes), Res is NewRes * Base.
