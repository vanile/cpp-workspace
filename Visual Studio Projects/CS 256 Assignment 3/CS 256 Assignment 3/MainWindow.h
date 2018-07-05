#pragma once
#include "PostFixEval.cpp"
#include "Test.cpp"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <msclr\marshal_cppstd.h>
#include <sstream>

using namespace System;
using namespace msclr::interop;
using std::vector;

namespace CS256Assignment3 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MainWindow
	/// </summary>
	public ref class MainWindow : public System::Windows::Forms::Form
	{
	public:
		MainWindow(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MainWindow()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::Button^  btnCreate;
	protected:
	private: System::Windows::Forms::Button^  btnDirectory;
	private: System::Windows::Forms::Button^  btnSave;
	private: System::Windows::Forms::Button^  btnRun;
	private: System::Windows::Forms::Button^  btnVarList;
	private: System::Windows::Forms::TextBox^  textBox1;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::TextBox^  textBox2;
	private: System::Windows::Forms::Label^  label2;
	private: System::Windows::Forms::Button^  btnEnter;
	private: System::Windows::Forms::Label^  label3;
	private: System::Windows::Forms::Label^  label4;




	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->btnCreate = (gcnew System::Windows::Forms::Button());
			this->btnDirectory = (gcnew System::Windows::Forms::Button());
			this->btnSave = (gcnew System::Windows::Forms::Button());
			this->btnRun = (gcnew System::Windows::Forms::Button());
			this->btnVarList = (gcnew System::Windows::Forms::Button());
			this->textBox1 = (gcnew System::Windows::Forms::TextBox());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->textBox2 = (gcnew System::Windows::Forms::TextBox());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->btnEnter = (gcnew System::Windows::Forms::Button());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->SuspendLayout();
			// 
			// btnCreate
			// 
			this->btnCreate->Location = System::Drawing::Point(465, 89);
			this->btnCreate->Name = L"btnCreate";
			this->btnCreate->Size = System::Drawing::Size(75, 23);
			this->btnCreate->TabIndex = 0;
			this->btnCreate->Text = L"Create";
			this->btnCreate->UseVisualStyleBackColor = true;
			this->btnCreate->Click += gcnew System::EventHandler(this, &MainWindow::btnCreate_Click);
			// 
			// btnDirectory
			// 
			this->btnDirectory->Location = System::Drawing::Point(509, 167);
			this->btnDirectory->Name = L"btnDirectory";
			this->btnDirectory->Size = System::Drawing::Size(75, 23);
			this->btnDirectory->TabIndex = 1;
			this->btnDirectory->Text = L"Directory";
			this->btnDirectory->UseVisualStyleBackColor = true;
			this->btnDirectory->Click += gcnew System::EventHandler(this, &MainWindow::btnDirectory_Click);
			// 
			// btnSave
			// 
			this->btnSave->Location = System::Drawing::Point(546, 89);
			this->btnSave->Name = L"btnSave";
			this->btnSave->Size = System::Drawing::Size(75, 23);
			this->btnSave->TabIndex = 2;
			this->btnSave->Text = L"Save";
			this->btnSave->UseVisualStyleBackColor = true;
			this->btnSave->Click += gcnew System::EventHandler(this, &MainWindow::btnSave_Click);
			// 
			// btnRun
			// 
			this->btnRun->Location = System::Drawing::Point(465, 128);
			this->btnRun->Name = L"btnRun";
			this->btnRun->Size = System::Drawing::Size(75, 23);
			this->btnRun->TabIndex = 3;
			this->btnRun->Text = L"Run";
			this->btnRun->UseVisualStyleBackColor = true;
			this->btnRun->Click += gcnew System::EventHandler(this, &MainWindow::btnRun_Click);
			// 
			// btnVarList
			// 
			this->btnVarList->Location = System::Drawing::Point(546, 128);
			this->btnVarList->Name = L"btnVarList";
			this->btnVarList->Size = System::Drawing::Size(75, 23);
			this->btnVarList->TabIndex = 4;
			this->btnVarList->Text = L"Var List";
			this->btnVarList->UseVisualStyleBackColor = true;
			this->btnVarList->Click += gcnew System::EventHandler(this, &MainWindow::btnVarList_Click);
			// 
			// textBox1
			// 
			this->textBox1->Location = System::Drawing::Point(38, 36);
			this->textBox1->Multiline = true;
			this->textBox1->Name = L"textBox1";
			this->textBox1->ReadOnly = true;
			this->textBox1->ScrollBars = System::Windows::Forms::ScrollBars::Both;
			this->textBox1->Size = System::Drawing::Size(403, 228);
			this->textBox1->TabIndex = 5;
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Location = System::Drawing::Point(35, 20);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(39, 13);
			this->label1->TabIndex = 6;
			this->label1->Text = L"Viewer";
			// 
			// textBox2
			// 
			this->textBox2->Location = System::Drawing::Point(38, 323);
			this->textBox2->Name = L"textBox2";
			this->textBox2->Size = System::Drawing::Size(403, 20);
			this->textBox2->TabIndex = 7;
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Location = System::Drawing::Point(35, 307);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(31, 13);
			this->label2->TabIndex = 8;
			this->label2->Text = L"Input";
			// 
			// btnEnter
			// 
			this->btnEnter->Location = System::Drawing::Point(465, 323);
			this->btnEnter->Name = L"btnEnter";
			this->btnEnter->Size = System::Drawing::Size(75, 23);
			this->btnEnter->TabIndex = 9;
			this->btnEnter->Text = L"Open File";
			this->btnEnter->UseVisualStyleBackColor = true;
			this->btnEnter->Click += gcnew System::EventHandler(this, &MainWindow::btnEnter_Click);
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Location = System::Drawing::Point(387, 353);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(256, 13);
			this->label3->TabIndex = 10;
			this->label3->Text = L"Before clicking Create, enter a filename into the input";
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(0)));
			this->label4->Location = System::Drawing::Point(485, 30);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(55, 25);
			this->label4->TabIndex = 11;
			this->label4->Text = L"Main";
			// 
			// MainWindow
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(655, 375);
			this->Controls->Add(this->label4);
			this->Controls->Add(this->label3);
			this->Controls->Add(this->btnEnter);
			this->Controls->Add(this->label2);
			this->Controls->Add(this->textBox2);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->textBox1);
			this->Controls->Add(this->btnVarList);
			this->Controls->Add(this->btnRun);
			this->Controls->Add(this->btnSave);
			this->Controls->Add(this->btnDirectory);
			this->Controls->Add(this->btnCreate);
			this->Name = L"MainWindow";
			this->Text = L"MainWindow";
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void btnDirectory_Click(System::Object^  sender, System::EventArgs^  e) {
		label3->Text = "Enter the file name into the input and click Enter to open";
		label4->Text = "Directory Mode";
		textBox1->ReadOnly = true;
		textBox1->Text = "";

		ifstream list;
		list.open("List.txt", ios::in | ios::binary);

		string item;
		while (!list.eof())
		{
			item = "";
			list >> item;
			String^ output = marshal_as<String^>(item);
			textBox1->Text += output + "\r\n";
		}
		list.close();
	}
	private: System::Void btnRun_Click(System::Object^  sender, System::EventArgs^  e) {
		if (createNewFile == "")
		{
			MessageBox::Show("You must save the file first!");
		}

		ifstream fileOpener;
		PostFixEvaluator evaluator;
		vector<float> vars;
		float output;

		String^ pOpenFile = textBox2->Text;
		string openFile = msclr::interop::marshal_as<string>(pOpenFile);

		fileOpener.open(openFile.c_str(), ios::in | ios::binary);

		if (fileOpener.is_open())
		{
			string item;
			while (!fileOpener.eof())
			{
				getline(fileOpener, item);
				output = evaluator.PostFixEval(item);
				vars.push_back(output);

			}
			fileOpener.close();
			createNewFile = textBox2->Text;
			MessageBox::Show("Run complete");
		}
		else
		{
			MessageBox::Show("Unable to open file, perhaps an incorrect filename was entered.");
		}
	}
	private: System::Void btnVarList_Click(System::Object^  sender, System::EventArgs^  e) {
		if (createNewFile == "")
		{
			MessageBox::Show("You must save the file first!");
		}

		ifstream fileOpener;
		PostFixEvaluator evaluator;

		float output;

		String^ pOpenFile = textBox2->Text;
		string openFile = msclr::interop::marshal_as<string>(pOpenFile);

		fileOpener.open(openFile.c_str(), ios::in | ios::binary);
		if (fileOpener.is_open())
		{
			textBox1->Text = "";
			string item;
			while (!fileOpener.eof())
			{
				getline(fileOpener, item);
				String^ oVar = marshal_as<String^>(item);
				output = evaluator.PostFixEval(item);
			}
			
			for (unsigned int i = 0; i < evaluator.listVar.size(); i++)
			{
				stringstream ss(stringstream::in | stringstream::out);
				std::string var = evaluator.listVar.at(i).name;
				float value = evaluator.listVar.at(i).output;
				ss << value;
				string nValue = ss.str();

				var = var + "   " + nValue + "\r\n";
				String^ oVar = marshal_as<String^>(var);
				textBox1->Text += oVar;
			}
			fileOpener.close();
			createNewFile = textBox2->Text;
		}
		else
		{
			MessageBox::Show("Unable to open file, perhaps an incorrect filename was entered.");
		}
	}
	private: System::Void btnCreate_Click(System::Object^  sender, System::EventArgs^  e) {
		textBox1->Text = "";
		if (textBox2->Text == "")
		{
			MessageBox::Show("You need to enter a filename into the input first!");
		}
		label4->Text = "Create Mode";
		textBox1->ReadOnly = false;
		label3->Text = "You can now edit in the viewer, you must save before you run";
		createNewFile = textBox2->Text;

	}
	private: System::Void btnSave_Click(System::Object^  sender, System::EventArgs^  e) {
		if (createNewFile == "")
		{
			MessageBox::Show("You need to create a new file first!");
			
		}
		ifstream list;
		ofstream newText;
		ofstream out;
		bool flag = false;

		string listName = "List.txt";
		//list.open(listName.c_str(), ios:: in | ios::binary);

		string sLine;
		String^ newName = createNewFile;
		string newTextS = msclr::interop::marshal_as<string>(newName);
		list.close();
		newText.open(newTextS.c_str(), ios::out | ios::binary);
		std::string yes = msclr::interop::marshal_as<std::string>(textBox1->Text);
		newText.write(yes.c_str(), yes.length());

		//add to directory
		out.open(listName.c_str(), std::ios::app);

		out << newTextS << endl;
		out.close();

	}
	public:
		String^ createNewFile = "";

	private: System::Void btnEnter_Click(System::Object^  sender, System::EventArgs^  e) {
		ifstream fileOpener;

		textBox1->Text = "";
		String^ pOpenFile = textBox2->Text;
		string openFile = msclr::interop::marshal_as<string>(pOpenFile);

		fileOpener.open(openFile.c_str(), ios::in | ios::binary);

		if (fileOpener.is_open())
		{
			label4->Text = "Edit Mode";
			textBox1->ReadOnly = false;
			string item;
			while (!fileOpener.eof())
			{
				getline(fileOpener, item);
				String^ output = marshal_as<String^>(item);
				textBox1->Text += output + "\r\n";
			}
			fileOpener.close();
			createNewFile = textBox2->Text;
		} 
		else
		{
			MessageBox::Show("Unable to open file, perhaps an incorrect filename was entered.");
		}
	}
};
}
