# Pragmatic Programmer

In this repository I want to share my solutions to the exercises provided by the book "Pragmatic Programmer" by Andrew Hunt and David Thomas. 

##Exercise 13
Goal of exercise 13 is to write a code generator that creates a source file in a specific programming language for message defined by a given schema. The exercise defines the schema as follows: 

A line that starts with: 
* a `#` is followed by a comment
* an `M` is followed by the name of the message
* an `F` is followed by the name and the type of field in that message
* an `E` defines the end of the message.

Since there were no further information and in the example the types in the schema matched those in the resulting C code, I assumed that the types of the fields in the scheme correspond to C types. So I mapped them 1:1. Since the Pascal types in the example are somewhat different, I mapped those types accordingly. Since I am neither a Pascal nor a C programmer, I only added those types that appear in the example. See [org.example.CodeGeneratorTest](https://github.com/johannesn/pragmaticprogrammer/blob/master/Exercise13/src/test/java/org/example/CodeGeneratorTest.java) for examples how to use my code. 

![build Exercise 13](https://github.com/johannesn/pragmaticprogrammer/workflows/build%20Exercise%2013/badge.svg?branch=master)
![test Exercise 13](https://github.com/johannesn/pragmaticprogrammer/workflows/test%20Exercise%2013/badge.svg?branch=master)
