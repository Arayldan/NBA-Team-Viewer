#!/bin/sh

export PATH=/usr/local/bin:$PATH

echo "Running static analysis..."

# Format code using KtLint, then run Detekt and KtLint static analysis

git diff --name-only --cached --relative | grep '\.kt[s"]\?$' | xargs ktlint -F --relative . &&\
./gradlew detekt --daemon

status=$?

if [[ "$status" = 0 ]] ; then
    echo "Static analysis found no problems."
    exit 0
else
    echo 1>&2 "Static analysis found violations it could not fix."
    exit 1
fi
