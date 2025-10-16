-- ============================================
-- Dispatch 도메인 테이블 생성
-- ============================================

-- 1. dispatch_requests 테이블 (ConcurrentEntity 상속)
CREATE TABLE dispatch_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    assigned_agent_id BIGINT NULL,
    dispatched_at TIMESTAMP(6) NULL,
    cancelled_at TIMESTAMP(6) NULL,
    
    -- BaseEntity 공통 필드
    entity_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    
    -- ConcurrentEntity 추가 필드 (낙관적 락)
    version BIGINT NOT NULL DEFAULT 0,
    
    -- 인덱스
    INDEX idx_dispatch_requests_order_id (order_id),
    INDEX idx_dispatch_requests_status (status),
    INDEX idx_dispatch_requests_assigned_agent_id (assigned_agent_id),
    INDEX idx_dispatch_requests_entity_status (entity_status),
    INDEX idx_dispatch_requests_created_at (created_at)
);

-- 2. dispatch_proposals 테이블 (BaseEntity 상속)
CREATE TABLE dispatch_proposals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dispatch_request_id BIGINT NOT NULL,
    proposal_status VARCHAR(50) NOT NULL,
    completed_at TIMESTAMP(6) NULL,
    
    -- BaseEntity 공통 필드
    entity_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    
    -- 외래키 제약조건
    FOREIGN KEY (dispatch_request_id) REFERENCES dispatch_requests(id),
    
    -- 인덱스
    INDEX idx_dispatch_proposals_dispatch_request_id (dispatch_request_id),
    INDEX idx_dispatch_proposals_proposal_status (proposal_status),
    INDEX idx_dispatch_proposals_entity_status (entity_status),
    INDEX idx_dispatch_proposals_created_at (created_at)
);

-- 3. agent_proposals 테이블 (BaseEntity 상속)
CREATE TABLE agent_proposals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dispatch_proposal_id BIGINT NOT NULL,
    agent_id BIGINT NOT NULL,
    suggested_price DECIMAL(10, 2) NOT NULL,
    proposal_status VARCHAR(50) NOT NULL,
    proposed_at TIMESTAMP(6) NOT NULL,
    expires_at TIMESTAMP(6) NOT NULL,
    responded_at TIMESTAMP(6) NULL,
    
    -- BaseEntity 공통 필드
    entity_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    
    -- 외래키 제약조건
    FOREIGN KEY (dispatch_proposal_id) REFERENCES dispatch_proposals(id),
    
    -- 인덱스
    INDEX idx_agent_proposals_dispatch_proposal_id (dispatch_proposal_id),
    INDEX idx_agent_proposals_agent_id (agent_id),
    INDEX idx_agent_proposals_proposal_status (proposal_status),
    INDEX idx_agent_proposals_proposed_at (proposed_at),
    INDEX idx_agent_proposals_expires_at (expires_at),
    INDEX idx_agent_proposals_entity_status (entity_status),
    INDEX idx_agent_proposals_created_at (created_at),
    
    -- 복합 인덱스
    INDEX idx_agent_proposals_agent_status (agent_id, proposal_status)
);

-- 테이블 코멘트
ALTER TABLE dispatch_requests COMMENT = '배차 요청 테이블';
ALTER TABLE dispatch_proposals COMMENT = '배차 제안 테이블';
ALTER TABLE agent_proposals COMMENT = '기사 제안 테이블';