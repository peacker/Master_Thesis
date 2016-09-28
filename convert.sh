# this script convert all java file from ISO-8859-1 to UTF-8 format

for file in source/*.java
do
    iconv -f ISO-8859-1 -t UTF-8 -o "$file.new" "$file" &&
    mv -f "$file.new" "$file"
done
