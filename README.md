Package Info
============

Browse through a list of Ubuntu/Debian packages and view information like
package description, dependencies, reverse dependencies, etc.

Compiling
---------

Running ``ant`` compiles the project and creates the executable JAR file
``package-info.jar``.

My Usage Scenario
-----------------

I keep a list of packages I install by default. When I do not properly maintain
the list for a while I have to check manually which of the newly installed
packages I want to include in my list.

1. Suppose you have an old snapshot of installed packages (e.g., after a fresh
   installation) in ``previous-packages``.

2. Create a snapshot of the currently installed packages in ``current-packages``.
   
   ```
   dpkg --get-selections | grep -v deinstall | grep -o "^[^[:space:]]*" | sort > current-packages
   ```

3. Extract the newly installed packages into ``added-packages``.
   
   ```
   comm -13 previous-packages current-packages > added-packages
   ```
   
   Note that both files have to be sorted. You can proceed similarly to further
   remove packages you know you do not need to check.

4. Browse through the remaining list of packages.
   
   ```
   java -jar package-info.jar added-packages
   ```
   
   Hit the DEL key to remove packages from the list while browsing. The final
   list can be copied to the clipboard.
