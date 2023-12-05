@echo off
for /r %%i in (*.class) do del "%%i"
echo All .class files have been removed.
