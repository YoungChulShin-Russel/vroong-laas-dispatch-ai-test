-- ============================================
-- Dispatch 도메인 테이블 생성
-- ============================================

-- 1. dispatches 테이블 (ConcurrentEntity 상속)
CREATE TABLE dispatches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    agent_id BIGINT NULL,
    requested_at TIMESTAMP(6) NULL,
    dispatched_at TIMESTAMP(6) NULL,
    cancelled_at TIMESTAMP(6) NULL,
    
    -- BaseEntity 공통 필드
    entity_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    
    -- ConcurrentEntity 추가 필드 (낙관적 락)
    version BIGINT NOT NULL DEFAULT 0,
    
    -- 인덱스
    INDEX idx_dispatches_order_id (order_id),
    INDEX idx_dispatches_status (status),
    INDEX idx_dispatches_agent_id (agent_id),
    INDEX idx_dispatches_entity_status (entity_status),
    INDEX idx_dispatches_created_at (created_at)
);

-- 2. dispatch_proposals 테이블 (BaseEntity 상속)
CREATE TABLE dispatch_proposals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dispatch_id BIGINT NOT NULL,
    agent_id BIGINT NOT NULL,
    suggested_fee DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    proposed_at TIMESTAMP(6) NOT NULL,
    expires_at TIMESTAMP(6) NOT NULL,
    responded_at TIMESTAMP(6) NULL,
    
    -- BaseEntity 공통 필드
    entity_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    
    -- 외래키 제약조건
    FOREIGN KEY (dispatch_id) REFERENCES dispatches(id),
    
    -- 인덱스
    INDEX idx_dispatch_proposals_dispatch_id (dispatch_id),
    INDEX idx_dispatch_proposals_agent_id (agent_id),
    INDEX idx_dispatch_proposals_status (status),
    INDEX idx_dispatch_proposals_proposed_at (proposed_at),
    INDEX idx_dispatch_proposals_expires_at (expires_at),
    INDEX idx_dispatch_proposals_entity_status (entity_status),
    INDEX idx_dispatch_proposals_created_at (created_at),
    
    -- 복합 인덱스
    INDEX idx_dispatch_proposals_agent_status (agent_id, status)
);

-- 테이블 코멘트
ALTER TABLE dispatches COMMENT = '배차 테이블';
ALTER TABLE dispatch_proposals COMMENT = '배차 제안 테이블';