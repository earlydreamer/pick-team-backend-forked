package com.pickteam.repository.kanban;

import com.pickteam.domain.kanban.KanbanTaskAttach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KanbanTaskAttachRepository extends JpaRepository<KanbanTaskAttach, Long> {

    @Query("SELECT kta FROM KanbanTaskAttach kta WHERE kta.kanbanTask.id = :kanbanTaskId AND kta.isDeleted = false")
    List<KanbanTaskAttach> findByKanbanTaskId(@Param("kanbanTaskId") Long kanbanTaskId);

    // 파일이 첨부된 칸반의 워크스페이스 ID 조회 (다운로드 권한 검증용)
    @Query("SELECT kta.kanbanTask.kanbanList.kanban.workspace.id FROM KanbanTaskAttach kta " +
            "WHERE kta.fileInfo.id = :fileInfoId AND kta.isDeleted = false")
    Optional<Long> findWorkspaceIdByFileInfoId(@Param("fileInfoId") Long fileInfoId);
}