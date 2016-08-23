#!/bin/sh
case $1 in
    classes)
        mkdir out
        for file in *.java
        do
           javac $file -d out/
        done
        ;;
    clean)
        rm -rf out
        ;;
    esac
