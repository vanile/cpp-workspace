#include "MainWindow.h"

using namespace System;
using namespace System::Windows::Forms;

[STAThread]
void Main(array<String ^> ^ args)
{
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false);
	CS256Assignment3::MainWindow myForm;
	Application::Run(%myForm);
}