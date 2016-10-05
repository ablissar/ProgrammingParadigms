#!/bin/bash
set -u -e
echo ""
echo ""
echo "Building"
javac Controller.java
echo ""
echo ""
echo "Running"
java Controller
