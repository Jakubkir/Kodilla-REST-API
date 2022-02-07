call runcad.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo You have a problem with runcrud.bat
goto fail

:browser
chrom "http://localhost:8080/crud/v1/task/getTasks"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.