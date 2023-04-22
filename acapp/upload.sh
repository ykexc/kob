scp dist/js/*.js sb:kob/acapp/
scp dist/css/*.css sb:kob/acapp/

ssh sb 'cd kob/acapp && ./rename.sh'
