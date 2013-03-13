package com.wellmail.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.bean.WebFileBean;
import com.wellmail.dao.FileTypeDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.dao.WebDiskDao;
import com.wellmail.dao.WebFileDao;
import com.wellmail.model.FileType;
import com.wellmail.model.Users;
import com.wellmail.model.WebDisk;
import com.wellmail.model.WebFile;

public class DeletedFolderAction extends Action {

	private UsersDao usersDao;
	private Users users;
	
	private WebDiskDao webDiskDao;
	private WebDisk webdisk;
	
	private FileTypeDao fileTypeDao;
	private FileType filetype;
	
	private WebFileDao webFileDao;
	
	private WebFileBean webfilebean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		users  = (Users) request.getSession().getAttribute("user");
		users = usersDao.queryUserByName(users);
		
		
		//ɾ���ļ���
		String deletefolder[] = request.getParameterValues("mid");
		
		for(int i = 0; i<deletefolder.length; i++) {
			filetype.setFiletypeid(Integer.parseInt(deletefolder[i]));
			filetype = fileTypeDao.queryFileTypeByFTId(filetype);
			
			if(filetype.getFiletypename().equals("�ҵ��ĵ�")) {
				
				request.setAttribute("flag", "delfoldererror");
				return mapping.findForward("failure");
			}else if(filetype.getFiletypename().equals("�ҵ�ͼƬ")) {
				
				request.setAttribute("flag", "delfoldererror");
				return mapping.findForward("failure");
			}else if(filetype.getFiletypename().equals("�ҵ�����")) {
				
				request.setAttribute("flag", "delfoldererror");
				return mapping.findForward("failure");
			}else if(filetype.getFiletypename().equals("�ҵĶ�ý��")) {
				
				request.setAttribute("flag", "delfoldererror");
				return mapping.findForward("failure");
			}else if(filetype.getFiletypename().equals("�ҵ����")) {
				
				request.setAttribute("flag", "delfoldererror");
				return mapping.findForward("failure");
			}
			
		}
		
		for(int i = 0; i<deletefolder.length; i++) {
			filetype.setFiletypeid(Integer.parseInt(deletefolder[i]));
			filetype = fileTypeDao.queryFileTypeByFTId(filetype);
			
			fileTypeDao.delWebDiskFolder(filetype);
		}
		
		//��ѯ
		webdisk = webDiskDao.queryWebDiskByUname(users);
		
		List<FileType> fileTypeList = fileTypeDao.queryFileTypeByUname(users);
		List<WebFileBean> webFileBeanList = new ArrayList<WebFileBean>();
		
		if(fileTypeList.size() > 0) {
			
			for(Iterator<FileType> i = fileTypeList.iterator();i.hasNext();) {
				filetype = i.next();
				//System.out.println(filetype.getFiletypename());
				webfilebean = new WebFileBean();
				List<WebFile> webFileList = webFileDao.queryWebFileByUnameAndFileType(users, filetype);
				webfilebean.setFiletype(filetype);
				webfilebean.setWebFileList(webFileList);
				webFileBeanList.add(webfilebean);
				webfilebean = null;
			}
			
			//webfilebean = null;
			
		}else {
			return mapping.findForward("failure");
		}
		
		request.getSession().setAttribute("webfilebeanlist", webFileBeanList);
		request.getSession().setAttribute("webdisk", webdisk);
		
		return mapping.findForward("success");
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setWebDiskDao(WebDiskDao webDiskDao) {
		this.webDiskDao = webDiskDao;
	}

	public void setWebdisk(WebDisk webdisk) {
		this.webdisk = webdisk;
	}

	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}

	public void setFiletype(FileType filetype) {
		this.filetype = filetype;
	}

	public void setWebFileDao(WebFileDao webFileDao) {
		this.webFileDao = webFileDao;
	}

	public void setWebfilebean(WebFileBean webfilebean) {
		this.webfilebean = webfilebean;
	}
	
}
