#!/bin/bash
scp -r dist/ sb:~/kob && ssh sb "cd kob && rm -r web/ && mv dist/ web"
