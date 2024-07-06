# Canela Emulator

This is my own testing version of cassiapp, I have no idea if I'll ever get the stuff to work. But if you want to use this anyway, just follow the instructions

## Build

* Just build the app using Android Studio lol

## Adding the stuff to Canela
* You'll have to build the [cassiaext](https://github.com/MixedVictor/cassiaext), and the [runtime](https://github.com/MixedVictor/cassiaruntime) from my own forks using VSCode's Dev Containers.
* On cassiaext, grab the `prefix.tar.gz` from cassiaext inside the `build` folder
* And on the runtime, you'll have to zip the `prefix` folder that is inside `build` to `tar.gz`

After, that, just import them to Canela.

### What about the prefix?

For some reason, WINE doesn't want to create the damn prefix.
You can start your own prefix using your the WINE from your Termux (if you have one installed)
