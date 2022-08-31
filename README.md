# account-game-resolution-switcher
If you find any issues please post them in the issues section, also star this repository if you like the application - download [here](https://github.com/Stryzhh/account-game-resolution-switcher/releases).

## How does it work?
Account switching in short terms is simple. It swaps out files and registry values, pointing to your last logged in account while the program is closed. Think of it as freezing a platform like Steam in time, and replacing the "account block" with a previously frozen "account block", then unfreezing it. To Steam and your Steam account, it "looks" like you pulled your plug out and put it back in a week or so later, meanwhile you were playing on other accounts. Swapping the account block lets the program: Not interact with passwords, and not interact with 2-factor, so you can "Skip" both of those in the login process. 

For the resolution change, it simply uses a JNI to access low level operations which allows you to change your resolution easily and be done accessed using a shortcut rather then loading up an application such as "Nvidia Control Panel" skipping the hassle.

Loading a game is the easiest of all the three, your computer simply uses an internet shortcut (.url) to load a game linked with the specificly entered id, every game on steam has an id. You can obtain an id for your specific game using [steamDB](https://steamdb.info/) simply enter the name of the game and find the corresponding appid.

Combining all these together by simply clicking one button you can simultaneously change steam account, resolution and load up a game saving your a tremeendous amount of time and skipping all the hassle.

## What's New
Version 1.0 is released (Sept 2022)! Please report any and all bugs, as well as steps to recreate them into the Issues section.

**Featuring**
 * Cleaner and overall better UI, with implementation of basic animations. 
 * Automatically grabs steam app info using steam web API.

Again... for info on how this app works, visit the [Wiki](https://github.com/Stryzhh/account-game-resolution-switcher/wiki).

## Overview
Change steam-account, resolution and/or load up a game faster than ever before by saving profiles and accessing them via desktop, start menu or pinned tiles.

## Features

The features of this program:

* Create profiles
  * Add steam account, resolution and/or game to a profile.
  * Creates a .lnk (shortcut) to load profile and execute appropriate actions.
  * Automatically gathers appropriate images for each profile.

* Manage profiles
  * Load up profile (loads steam account, changes resolution and/or loads game)
  * Create up to 8 profiles.
  * Create a desktop shortcut for your profile.
  * Create a start menu shortcut  for your profile.
  * Delete profiles.

* Ease of access
  * Can automatically load upon windows startup.
  * Can automatically create desktop shortcut.
  * Can automatically create start menu.

* Most effecient transition
  * Uses c++ code via JNI to change system resolution.
  * Desktop and other entities stay sorted upon resolution change.
  * Will load applications with resolution rather than switching.

* Safe and Secure
  * Saves no passwords or any other information.
  * Doesn't change or corrupt any files.

* GUI
  * Simple and nice-looking.
  * Implementation of animations.
  * Easy to use.

## Watch a video
You can view a video of this application [here](https://www.youtube.com/watch?v=dQw4w9WgXcQ)

## Help, Informations and FAQ
Please note you need to download [resolution-switcher](https://github.com/Stryzhh/resolution-switcher) and [tcno-acc-switcher](https://github.com/TcNobo/TcNo-Acc-Switcher) for this application to function correctly.

For more information visit [here](https://github.com/Stryzhh/account-game-resolution-switcher/wiki) for this apps wiki.

## Author(s)
* [Dafydd Maund](https://github.com/Stryzhh)
