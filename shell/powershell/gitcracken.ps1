function Test-Command( [string] $CommandName ) {
	(Get-Command $CommandName -ErrorAction SilentlyContinue) -ne $null
}

$userLocation = Get-Location;

Set-Location $PSScriptRoot;

if (!(Test-Command("git"))) {
	Write-Host "===> @= ERROR ȱʧ Git <===" -ForegroundColor red;
	exit;
}

if (!(Test-Command("yarn"))) {
	Write-Host "===> @= ERROR%b ȱʧ node/yarn <===" -ForegroundColor red;
	exit;
}

if (!(Test-Command("sudo"))) {
	Write-Host "===> @= ERROR%b ȱʧ sudo <===" -ForegroundColor red;
	exit;
}

Write-Host "===> @= �������� <===" -ForegroundColor blue;
if ( Test-Path ./GitCracken ) {
	Set-Location ./GitCracken;
	git checkout -- .;
	git pull;
} else {
	Write-Host "@= ��ʼ��GitCracken�����" -ForegroundColor blue;
	git clone https://github.com/5cr1pt/GitCracken.git;
	Set-Location ./GitCracken;
}
Set-Location ./GitCracken;

Write-Host "===> @= ���ڹ�������Ӧ�� <===" -ForegroundColor blue;
yarn install;
yarn build;

Write-Host "===> @= ���ڽ����Զ��ƽ��� <===" -ForegroundColor blue;
sudo node dist/bin/gitcracken.js patcher;

Set-Location $userLocation;
