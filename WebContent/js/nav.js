	// �����������ļ�
	var outlookbar=new outlook();
	var t;
	t=outlookbar.addtitle('��������','ϵͳ����',1)
	outlookbar.additem('д��',t,'writetemp.jsp')
	outlookbar.additem('�ռ���',t,'goreceive.do')
	outlookbar.additem('�ݸ���',t,'goscript.do')
	outlookbar.additem('�ѷ���',t,'gosent.do')
	outlookbar.additem('��ɾ��',t,'godeleted.do')
	outlookbar.additem('�����ʼ�',t,'gorubbish.do')
	outlookbar.additem('����ʼ�',t,'goad.do')
	
	t=outlookbar.addtitle('������','ϵͳ����',1)
	outlookbar.additem('�����������',t,'addothermailbox.do')
	outlookbar.additem('�鿴��������',t,'goothermailbox.do')
	
	t=outlookbar.addtitle('�������','ϵͳ����',1)
	outlookbar.additem('��ϵ��',t,'gocontactbox.do')
	outlookbar.additem('���±�',t,'gonotebook.do')
	outlookbar.additem('����',t,'gowebdiskfolder.do')
