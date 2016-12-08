#!/bin/bash
echo "Test
Compile Ü07 
"
javac Tree.java

echo "Run Ü07 mit Parametern 't1.dot t2.dot'
	"

java Tree t1.dot t2.dot

echo "Run Converter for dot-Files
"

dot -Tpdf t1.dot > t1.pdf
dot -Tpdf t2.dot > t2.pdf

echo "Open Files
"

xdg-open t1.pdf
xdg-open t2.pdf