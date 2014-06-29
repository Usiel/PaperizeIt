#!/bin/bash

curl -o datatemp.xml http://localhost:9000/pdfgenerator/index?user=test@paperize.it
JAVA_ARGS=-Dlog4j.configuration=log4j.properties sh /Users/thomas/Downloads/fop-1.1/fop -c /Users/thomas/Downloads/fop-1.1/conf/fop.xconf -xml datatemp.xml -xsl style.xsl -pdf newPaper.pdf

