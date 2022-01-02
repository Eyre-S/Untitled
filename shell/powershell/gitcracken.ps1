## GitCracken Installer
## by Sukazyo

# preload
$userLocation = Get-Location;
Set-Location $PSScriptRoot;

# function
function Test-Command( [string] $CommandName ) {
	(Get-Command $CommandName -ErrorAction SilentlyContinue) -ne $null
}

function safeexit () {
	Set-Location $userLocation;
	exit;
}

# if missing
$ifexit = $false;
if (!(Test-Command("git"))) {
	Write-Host "===> @= ERROR 缺失 Git <===" -ForegroundColor red;
	$ifexit = $true;
}

if (!(Test-Command("yarn"))) {
	Write-Host "===> @= ERROR%b 缺失 node/yarn <===" -ForegroundColor red;
	$ifexit = $true;
}

if (!(Test-Command("sudo"))) {
	Write-Host "===> @= ERROR%b 缺失 sudo <===" -ForegroundColor red;
	$ifexit = $true;
}

if ($ifexit) {
	safeexit;
}

# main
Write-Host "===> @= 检查更新中 <===" -ForegroundColor blue;
if ( Test-Path ./GitCracken ) {
	Set-Location ./GitCracken;
	git checkout -- .;
	git pull;
} else {
	Write-Host "@= 初始化GitCracken储存库" -ForegroundColor blue;
	git clone https://github.com/PMExtra/GitCracken.git;
	Set-Location ./GitCracken;
}
Set-Location ./GitCracken;

Write-Host "===> @= 正在构建本地应用 <===" -ForegroundColor blue;
yarn install;
yarn build;

Write-Host "===> @= 正在进行自动破解打包 <===" -ForegroundColor blue;
sudo node dist/bin/gitcracken.js patcher;

safeexit;
