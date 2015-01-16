package com.hz.yisheng.handchain.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.javafans.web.springmvc.controller.WebControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.handchain.bo.FriendCircleBO;
import com.hz.yisheng.handchain.orm.AttachmentBean;
import com.hz.yisheng.handchain.orm.DiscussInfoBean;
import com.hz.yisheng.handchain.orm.SwfEntityJson;
import com.hz.yisheng.handchain.orm.friendCircleBean;
import com.hz.yisheng.handchain.utils.JSONUtils;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 朋友圈相关操作
 * 
 * @author cxj
 *
 */
@Controller
@RequestMapping("/handchain/friend")
public class FriendCircleController extends BaseController {
	private static final String realpath = ResourceConfig
			.getSysConfig("fileUploadPath");
	private static final String temppath = realpath + File.separator + "tmp";
	private static final String type = "friend";
	private static final Integer discussMark = 0;
	private static final Integer praiseMark = 1;
	@Autowired
	private FriendCircleBO friendBO;

	/**
	 * 分页查询信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		Page<friendCircleBean> page = WebControllerUtils.preparePage(request,
				10);// 组装page对象
		page = friendBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(),
				response);
	}

	// 跳转新增和编辑页面
	@RequestMapping("/preview-update")
	public String previewUpdate(
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (id != null) {
			friendCircleBean fcb = friendBO.findFriendById(id);
			List<AttachmentBean> appendixlist = friendBO
					.getAttachmentByobjId(id);
			List<SwfEntityJson> swflist = new ArrayList<SwfEntityJson>();
			paramNoticeAppendixToSwfEntityJosn(appendixlist, swflist);
			String arrImg = JSONUtils.objToJSONReturnString(response, swflist);
			arrImg = arrImg.replaceAll("\"", "'");
			model.addAttribute("imgArr", arrImg);
			model.addAttribute("Objid", id);
			model.addAttribute("friendInfo", fcb);
		} else {
			friendCircleBean fcb = new friendCircleBean();
			model.addAttribute("friendInfo", fcb);
		}
		return "/friendCircle/input";
	}

	// 跳转评论页面
	@RequestMapping("/discussFriend")
	public String discussFriend(
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (id != null) {
			friendCircleBean fcb = friendBO.findFriendById(id);
			model.addAttribute("friendInfo", fcb);
			// 查询该条记录下所有的图片
			List<String> picList = null;
			List<AttachmentBean> attachList = friendBO
					.getAttachmentByobjIdAndType(id, type);
			if (attachList.size() > 0) {
				picList = new ArrayList<String>();
				for (int i = 0; i < attachList.size(); i++) {
					picList.add(attachList.get(i).getPath());
				}
			}
			model.addAttribute("picList", picList);
			model.addAttribute("picCount", attachList.size());
		}
		return "/friendCircle/discuss";
	}

	// 保存评论信息
	@RequestMapping("/saveDiscuss")
	public String saveDiscuss(@ModelAttribute friendCircleBean friend,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String discontent = request.getParameter("discussContent")
					.toString();
			DiscussInfoBean disinfo = new DiscussInfoBean();
			disinfo.setFriendId(friend.getId());
			disinfo.setContent(discontent);
			disinfo.setCreator(SessionHolder.getAdminUserId());
			disinfo.setMark(0);
			friendBO.insertDiscuss(disinfo);
			// 保存评论总数
			Long disCount = friendBO.findCountByFriendId(friend.getId(),
					discussMark);
			friend.setDiscussCount(disCount);
			friendBO.updateFriend(friend);
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			model.addAttribute("messager", "fail");
			e.printStackTrace();
		}
		return "/friendCircle/discuss";
	}

	@RequestMapping("/praiseFriend")
	public void praiseFriend(
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		String msg = "";
		try {
			// 保存点赞信息
			DiscussInfoBean praiseinfo = new DiscussInfoBean();
			praiseinfo.setFriendId(id);
			Long userid = SessionHolder.getAdminUserId();
			praiseinfo.setCreator(userid);
			praiseinfo.setMark(praiseMark);
			friendBO.insertDiscuss(praiseinfo);
			// 更新主表中点赞次数
			Long praiseCount = friendBO.findCountByFriendId(id, praiseMark);
			friendCircleBean friend = friendBO.findFriendById(id);
			friend.setPraiseCount(praiseCount);
			friendBO.updateFriend(friend);
			// 统计点赞总数，点赞人
			StringBuffer sb = new StringBuffer();
			List<DiscussInfoBean> priaselist = friendBO.findDiscussById(id,praiseMark);
			if (priaselist.size() > 0) {
				for (int i = 0; i < priaselist.size(); i++) {
					sb.append(priaselist.get(i).getUsername()).append(",");
				}
				msg = "点赞总人数： " + praiseCount + "      "+"人员列表如下： "+sb.toString();
			}else{
			   msg ="点赞成功！";
			}
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(msg);
		}
	}

	//查看
	@RequestMapping("/look")
	public String look(@RequestParam(value = "id", required = false) Long id,
	HttpServletRequest request, HttpServletResponse response,
	Model model) {
		if(id!=null){
			//查看主表信息
			friendCircleBean fcb = friendBO.findFriendById(id);
			model.addAttribute("friendInfo", fcb);
			// 查询该条记录下所有的图片
			List<String> picList = null;
			List<AttachmentBean> attachList = friendBO
					.getAttachmentByobjIdAndType(id, type);
			if (attachList.size() > 0) {
				picList = new ArrayList<String>();
				for (int i = 0; i < attachList.size(); i++) {
					picList.add(attachList.get(i).getPath());
				}
			}
			model.addAttribute("picList", picList);
			model.addAttribute("picCount", attachList.size());
			//查询评论信息
			List<DiscussInfoBean> disList = friendBO.findDiscussById(id, discussMark);
			model.addAttribute("disList", disList);
			model.addAttribute("disCount", disList.size());
			//查询点赞信息
			StringBuffer sb = new StringBuffer();
			String msg ="";
			String info ="";
			List<DiscussInfoBean> priaselist = friendBO.findDiscussById(id,praiseMark);
			if (priaselist.size() > 0) {
				for (int i = 0; i < priaselist.size(); i++) {
					sb.append(priaselist.get(i).getUsername()).append(",");
				}
				msg = "点赞总人数： " + priaselist.size(); 
				info = "人员列表如下： "+sb.toString();
			}else{
			   msg ="尚无点赞信息！";
			}
			model.addAttribute("praiseInfo", msg);
			model.addAttribute("info", info);
		}
		return "/friendCircle/look";
	}
	
	@RequestMapping("/save")
	public String save(String imgArr, @ModelAttribute friendCircleBean friend,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			if (friend.getId() != null) {
				Long userid = SessionHolder.getAdminUserId();
				friend.setUpdater(userid);
				// 上传图片,将图片信息
				uploadImage(friend, 1, imgArr);
				// 查询附件表中所有图片的路径
				StringBuffer s = new StringBuffer();
				List<AttachmentBean> attachList = friendBO
						.getAttachmentByobjIdAndType(friend.getId(), type);
				if (attachList.size() > 0) {
					for (int i = 0; i < attachList.size(); i++) {
						s.append(attachList.get(i).getPath()).append(",");
					}
					friend.setPic(s.toString());
				} else {
					friend.setPic("");
				}
				friendBO.updateFriend(friend);
			} else {
				Long userid = SessionHolder.getAdminUserId();
				friend.setCreator(userid);
				friendBO.insertFriend(friend);
				// 保存图片及其文件信息
				uploadImage(friend, 1, imgArr);
				// 查询附件表中所有图片的路径
				StringBuffer s = new StringBuffer();
				List<AttachmentBean> attachList = friendBO
						.getAttachmentByobjIdAndType(friend.getId(), type);
				if (attachList.size() > 0) {
					for (int i = 0; i < attachList.size(); i++) {
						s.append(attachList.get(i).getPath()).append(",");
					}
					friend.setPic(s.toString());
				} else {
					friend.setPic("");
				}
				friendBO.updateFriend(friend);
			}
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			model.addAttribute("messager", "fail");
			e.printStackTrace();
		}
		return "/friendCircle/input";
	}

	//删除操作
	/**
	 * 删除主表记录
	 * 删除相关评论记录
	 * 删除附件表中相关图片记录
	 * @param id
	 * @param response
	 * @param model
	 */
	@RequestMapping("/deleteFriend/{id}")
	public void deleteFriend(@PathVariable("id") Long id,
			HttpServletResponse response, Model model) {
		try {
			//删除评论、点赞记录
			friendBO.deleteDiscussByFriendId(id);
			//删除附件表相关图片记录
			friendBO.deleteAttachmentByobjIdAndType(id, type);
			//删除朋友圈主表记录
			friendBO.deleteFriend(id);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	// 删除附件
	@RequestMapping("deleteAttach")
	public void delete(
			@RequestParam(value = "imgArr", required = false) String imgArr,
			friendCircleBean friend, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			uploadImage(friend, 2, imgArr);
		} catch (Exception er) {
			er.printStackTrace();
			model.addAttribute("messager", "success");
		}
	}
	
	/**
	 * 多文件上传 returnOrCommit：用户是否点击保存操作，1代表保存，2代表返回
	 * */
	public void uploadImage(friendCircleBean friend, int returnOrCommit,
			String imgArr) {
		// 若返回的为新增数据，从临时文件移动图片；若为删除数据，直接删除images文件夹中的数据；若为修改数据，不做处理
		String[] fileUrl = imgArr.replaceAll("\\{", "").replaceAll("\"", "")
				.replaceAll("\\}", "").split(":|,");
		for (int i = 0; i < fileUrl.length - 1; i = i + 3) {
			String fileTypee = fileUrl[i].length() < 6 ? "" : fileUrl[i]
					.substring(0, 6);// 判断删除或者是新增或者是修改数据，根据fileUrl[i]的前六个字符：insert还是delete来判断
			String fileUrll = fileUrl[i + 1];
			String fileNamee = fileUrl[i + 2];
			// 新增附件
			if (fileTypee.equals("Insert")) {
				if (returnOrCommit != 2) // 点击保存
				{
					// 从临时文件夹下存入到指定的目录下
					try {
						File oldfile = new File(temppath + File.separator
								+ fileUrll);
						// oldfile.renameTo(new File(realpath + File.separator +
						// fileUrll));
						if (oldfile.exists()) {
							FileUtils.copyFile(oldfile, new File(realpath
									+ File.separator + fileUrll));
							boolean flag = oldfile.delete();
						}
					} catch (Exception er) {
						er.printStackTrace();
					}

					// 存入数据库
					AttachmentBean appendix = new AttachmentBean();
					appendix.setObjId(String.valueOf(friend.getId()));
					appendix.setType(type);
					appendix.setPath(fileUrll);
					appendix.setFileName(fileNamee);
					List<AttachmentBean> list = new ArrayList<AttachmentBean>();
					list.add(appendix);
					friendBO.insertAttachement(list);
				} else// 没点击保存，直接返回
				{
					// 从临时文件夹中删除
					File tempfile = new File(temppath + File.separator
							+ fileUrll);
					tempfile.delete();
				}
			} else if (fileTypee.equals("Delete")) {// 删除附件
				if (returnOrCommit != 2) // 点击保存
				{
					// 从临时文件夹中删除
					File tempfile = new File(temppath + File.separator
							+ fileUrll);
					if (tempfile.exists())
						tempfile.delete();
					// 从真正保存的文件夹中删除
					File realfile = new File(realpath + File.separator
							+ fileUrll);
					if (realfile.exists())
						realfile.delete();
					// 删除数据库中的信息
					friendBO.deleteAttachmentByobjIdAndpath(friend.getId()
							.toString(), fileUrll);
				} else// 没点击保存，直接返回
				{
					// 从临时文件夹中将图片重新存入实际文件夹
					File oldfile = new File(temppath + File.separator
							+ fileUrll);
					// oldfile.renameTo(new File(realpath + File.separator +
					// fileUrll));
					if (oldfile.exists()) {
						try {
							FileUtils.copyFile(oldfile, new File(realpath
									+ File.separator + fileUrll));
							oldfile.delete();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private void paramNoticeAppendixToSwfEntityJosn(
			List<AttachmentBean> appendixlist, List<SwfEntityJson> swflist) {
		if (appendixlist == null || swflist == null)
			return;
		for (int i = 0; i < appendixlist.size(); i++) {
			AttachmentBean app = appendixlist.get(i);
			SwfEntityJson swf = new SwfEntityJson();
			swf.setFileID("a" + app.getId() + i);
			swf.setFileUrl(app.getPath());
			swf.setFileName(app.getFileName());
			swf.setFileType(getExt(app.getFileName()));
			swf.setFileSize(getFileSize(app.getPath(), realpath));
			swflist.add(swf);
		}
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param url
	 * */
	private String getExt(String url) {
		if (url != null && url.length() > 0)
			return url.substring(url.lastIndexOf(".") + 1);
		return "";
	}

	/**
	 * 获取文件大小
	 * 
	 * @param url
	 *            文件路径
	 * @param realPath
	 *            文件实际路径
	 * */
	private long getFileSize(String url, String realPath) {
		if (url != null && realPath != null) {
			File f = new File(realPath + File.separator + url);
			if (f.exists())
				return f.length();
			else
				return 0L;
		} else
			return 0L;

	}
}
