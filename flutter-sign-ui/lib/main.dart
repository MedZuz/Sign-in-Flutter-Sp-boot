import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_application_2/secondpag.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(
    const MyApp(),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  var usernameController = TextEditingController();
  var passController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(10.0),
        child: SafeArea(
            child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextFormField(
                controller: usernameController,
                decoration: const InputDecoration(
                    labelText: "username",
                    border: OutlineInputBorder(),
                    suffixIcon: Icon(Icons.person)),
              ),
              const SizedBox(height: 15),
              TextFormField(
                controller: passController,
                obscureText: true, // dazz for the hide
                obscuringCharacter: '*', // hide charrrrrbrrrrbrbrbrbrbrb
                decoration: const InputDecoration(
                    labelText: "Password",
                    border: OutlineInputBorder(),
                    suffixIcon: Icon(Icons.password)),
              ),
              const SizedBox(
                height: 45,
              ),
              OutlinedButton.icon(
                onPressed: () {
                  login();
                },
                icon: const Icon(
                  Icons.login,
                  size: 18,
                ),
                label: const Text("Login"),
              )
            ],
          ),
        )),
      ),
    );
  }

  // fun to call login post api

  Future<void> login() async {
    if (passController.text.isNotEmpty && usernameController.text.isNotEmpty) {

      final boody = {
            'username': usernameController.text,
            'password': passController.text
          };

      var response = await http.post(Uri.parse("http://10.0.2.2:8080/api/login"),headers:<String,String>{"Content-type": "application/json"},
          body: jsonEncode(boody));


          
      if (response.statusCode == 200) {
         ScaffoldMessenger.of(context)
            .showSnackBar(const SnackBar(content: Text("sucess")));
       ;
      } else {
        ScaffoldMessenger.of(context)
            .showSnackBar(const SnackBar(content: Text("invalid credential")));
            print(response.statusCode);
      }
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text("All Fields are Required")));
    }
  }
}
