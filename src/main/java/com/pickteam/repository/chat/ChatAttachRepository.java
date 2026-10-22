package com.pickteam.repository.chat;

import com.pickteam.domain.chat.ChatAttach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatAttachRepository extends JpaRepository<ChatAttach, Long> {

    // 파일이 첨부된 채팅방의 워크스페이스 ID 조회 (다운로드 권한 검증용)
    @Query("SELECT ca.chatMessage.chatRoom.workspace.id FROM ChatAttach ca " +
            "WHERE ca.fileInfo.id = :fileInfoId AND ca.isDeleted = false")
    Optional<Long> findWorkspaceIdByFileInfoId(@Param("fileInfoId") Long fileInfoId);
}
