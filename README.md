# Compilers

grammars' directory has all the grammar these compilers use
tests' directory has all the tests (feel free to suggest more examples!)
I've provided jflex and cup .jars inside theirs respectives directories (see makefile section)
See Documentation to know more about topics about compilers

# MakeFile
Each project has 3 makefiles:
- makefile: main makefile, compile all the project and test all the test cases printing their exits inside .out files and after that delete all the temporal files
- makefile_delete: delete all the .out files
- makefile_zip: to zip the compiled project

# Project
Each project has 3 main files: .flex, .cup and .java and additionals classes depending of the grammar

# Recommended books:

- Compilers: Principles, Techniques, and Tools (2nd Edition) 
	by Alfred V. Aho  (Author), Monica S. Lam  (Author), 
	   Ravi Sethi  (Author), Jeffrey D. Ullman  (Author)

- Engineering: A Compiler 2nd Edition
	by Keith Cooper  (Author), Linda Torczon  (Author)

# Documentation

- More Documentation about JFLEX in http://www.jflex.de/docu.html
- More Documentation about CUP in http://www2.cs.tum.edu/projects/cup/docs.php
and about his intall and usual errors http://hackerdan.com/programing/using-java-cup/
